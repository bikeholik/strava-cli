import axios from 'axios';
import { getBasePath, Storage } from 'react-jhipster';

import { SERVER_API_URL } from 'app/config/constants';
import { AUTH_TOKEN_KEY, OAuthToken } from 'app/shared/reducers/authentication';

const TIMEOUT = 1 * 60 * 1000;
axios.defaults.timeout = TIMEOUT;
axios.defaults.baseURL = SERVER_API_URL;

const setupAxiosInterceptors = onUnauthenticated => {
  const onRequestSuccess = config => {
    const token: OAuthToken = Storage.local.get(AUTH_TOKEN_KEY) || Storage.session.get(AUTH_TOKEN_KEY);
    if (token) {
      config.headers.Authorization = `Bearer ${token.token}`;
    }
    return config;
  };
  const onResponseSuccess = response => response;
  const onResponseError = err => {
    const status = err.status || err.response.status;
    if (status === 403 || status === 401) {
      onUnauthenticated();
    }
    return Promise.reject(err);
  };
  axios.interceptors.request.use(onRequestSuccess);
  axios.interceptors.response.use(onResponseSuccess, onResponseError);
};

export default setupAxiosInterceptors;
