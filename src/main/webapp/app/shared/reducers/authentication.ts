import axios from 'axios';
import { Storage } from 'react-jhipster';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  LOGIN: 'authentication/LOGIN',
  GET_SESSION: 'authentication/GET_SESSION',
  LOGOUT: 'authentication/LOGOUT',
  CLEAR_AUTH: 'authentication/CLEAR_AUTH',
  ERROR_MESSAGE: 'authentication/ERROR_MESSAGE'
};

export const AUTH_TOKEN_KEY = 'oauth-token';

const initialState = {
  loading: false,
  isAuthenticated: false,
  loginSuccess: false,
  loginError: false, // Errors returned from server side
  showModalLogin: false,
  account: {} as any,
  errorMessage: null as string, // Errors returned from server side
  redirectMessage: null as string,
  sessionHasBeenFetched: false,
  idToken: null as string,
  logoutUrl: null as string
};

export type AuthenticationState = Readonly<typeof initialState>;

const initialToken = {
  type: null as string,
  expiresAt: 0,
  token: null as string,
  refreshToken: null as string
};

export type OAuthToken = Readonly<typeof initialToken>;

// Reducer

export default (state: AuthenticationState = initialState, action): AuthenticationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.LOGIN):
    case REQUEST(ACTION_TYPES.GET_SESSION):
      return {
        ...state,
        loading: true
      };
    case FAILURE(ACTION_TYPES.LOGIN):
      return {
        ...initialState,
        errorMessage: action.payload,
        showModalLogin: true,
        loginError: true
      };
    case FAILURE(ACTION_TYPES.GET_SESSION):
      return {
        ...state,
        loading: false,
        isAuthenticated: false,
        sessionHasBeenFetched: true,
        showModalLogin: true,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.LOGIN):
      return {
        ...state,
        loading: false,
        loginError: false,
        showModalLogin: false,
        loginSuccess: true,
        account: action.payload.data,
        sessionHasBeenFetched: true,
        isAuthenticated: true
      };
    case ACTION_TYPES.LOGOUT:
      return {
        ...initialState,
        showModalLogin: true
      };
    case SUCCESS(ACTION_TYPES.GET_SESSION): {
      return {
        ...state,
        isAuthenticated: true,
        loading: false,
        sessionHasBeenFetched: true,
        account: action.payload.data
      };
    }
    case ACTION_TYPES.ERROR_MESSAGE:
      return {
        ...initialState,
        showModalLogin: true,
        redirectMessage: action.message
      };
    case ACTION_TYPES.CLEAR_AUTH:
      return {
        ...state,
        loading: false,
        showModalLogin: true,
        isAuthenticated: false
      };
    default:
      return state;
  }
};

export const displayAuthError = message => ({ type: ACTION_TYPES.ERROR_MESSAGE, message });

export const getSession = () => async (dispatch, getState) => {
  const token : OAuthToken = Storage.local.get(AUTH_TOKEN_KEY);
  if(token) {
    if(token.expiresAt * 1000 > new Date().getTime()) {
      dispatch({
        type: ACTION_TYPES.GET_SESSION,
        payload: axios.get('cli/account')
      });
    } else {
      const result = await dispatch({
        type: ACTION_TYPES.GET_SESSION,
        payload: axios.post('api/refresh', transformRequest({
          refresh_token: token.refreshToken
        }), config)
      });
      saveToken(result, true);
    }
  }
};

const config = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
};

const transformRequest = (jsonData: object = {}) =>
    Object.entries(jsonData)
        .map(x => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
        .join('&');

const saveToken = (result, rememberMe) => {
  const payload = result.value.data;
  const token: OAuthToken = {
    type: payload.token_type,
    expiresAt: payload.expires_at,
    token: payload.access_token,
    refreshToken: payload.refresh_token
  };
  if (token.type === 'Bearer') {
    if (rememberMe) {
      Storage.local.set(AUTH_TOKEN_KEY, token);
    } else {
      Storage.session.set(AUTH_TOKEN_KEY, token);
    }
  }
};

export const login = (code, rememberMe = false) => async (dispatch, getState) => {
  const redirectBaseUrl = location.origin;
  const result = await dispatch({
    type: ACTION_TYPES.LOGIN,
    payload: axios.post(`${redirectBaseUrl}/api/authorize`, transformRequest({ code }), config)
  });
  // const bearerToken = result.value.headers.authorization;
  saveToken(result, rememberMe);
  // await dispatch(getSession());
};

export const clearAuthToken = () => {
  if (Storage.local.get(AUTH_TOKEN_KEY)) {
    Storage.local.remove(AUTH_TOKEN_KEY);
  }
  if (Storage.session.get(AUTH_TOKEN_KEY)) {
    Storage.session.remove(AUTH_TOKEN_KEY);
  }
};

export const logout = () => dispatch => {
  clearAuthToken();
  dispatch({
    type: ACTION_TYPES.LOGOUT
  });
};

export const clearAuthentication = messageKey => (dispatch, getState) => {
  clearAuthToken();
  dispatch(displayAuthError(messageKey));
  dispatch({
    type: ACTION_TYPES.CLEAR_AUTH
  });
};
