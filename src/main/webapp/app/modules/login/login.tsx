import React from 'react';
import { connect } from 'react-redux';
import { Redirect, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { login } from 'app/shared/reducers/authentication';

export interface ILoginProps extends StateProps, DispatchProps, RouteComponentProps<{}> {
    code: string;
}

export interface ILoginState {
    showModal: boolean;
    token: object;
    status: string;
}

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID;
const REDIRECT_URI = process.env.REACT_APP_REDIRECT_URI;
const AUTH_API_URI = process.env.REACT_APP_AUTH_API_URI;
const REGEXP = /(?:\?|&)code=([^&]+)/;
const STRAVA_BASE_URL = 'https://www.strava.com/oauth/authorize';
const SCOPES = 'read,activity:read,activity:write';

export class Login extends React.Component<ILoginProps, ILoginState> {
    state: ILoginState = {
        showModal: this.props.showModal,
        token: null,
        status: 'STATUS.INITIAL'
    };

    componentDidUpdate(prevProps: ILoginProps, prevState) {
        if (this.props !== prevProps) {
            this.setState({ showModal: this.props.showModal });
        }
    }

    handleLogin = (code, rememberMe = false) => {
        this.props.login(code, rememberMe);
    };

    handleClose = () => {
        this.setState({ showModal: false });
    };

    // render() {
    //   const { location, isAuthenticated } = this.props;
    //   const { from } = location.state || { from: { pathname: '/', search: location.search } };
    //   const { showModal } = this.state;
    //   if (isAuthenticated) {
    //     return <Redirect to={from} />;
    //   }
    //   return (
    //     <LoginModal showModal={showModal} handleLogin={this.handleLogin} handleClose={this.handleClose} loginError={this.props.loginError} />
    //   );
    // }

    componentDidMount() {
        // TODO in main ?
        // const storedToken = localStorage.getItem("github_token");
        // if (storedToken) {
        //   this.setState({
        //     status: STATUS.AUTHENTICATED
        //   });
        //   return;
        // }
        // const code =
        //     window.location.href.match(REGEXP) &&
        //     window.location.href.match(REGEXP)[1];
        const code = this.props.match.params.code;
        if (code) {
            // this.setState({ status: 'STATUS.LOADING' });
            // fetch(`${AUTH_API_URI}?code=${code}`, { method: 'post' })
            //     .then(response => response.json())
            //     .then(token => {
            //         if (token) {
            //             localStorage.setItem('github_token', token);
            //         }
            //         this.setState({
            //             status: 'STATUS.FINISHED_LOADING',
            //             token
            //         });
            //
            //         setTimeout(() => {
            //             this.setState({
            //                 status: 'STATUS.AUTHENTICATED'
            //             });
            //         }, 3000);
            //     });
            this.handleLogin(code);
        }
    }

    render() {
        const redirectBaseUrl = location.origin;
        if (this.state.status === 'STATUS.AUTHENTICATED' || this.props.isAuthenticated) {
            return (
                <Redirect to={{
                    pathname: '/',
                    search: ''
                }}
                />
                );
        }
        return (
            <div>
                <a
                    style={{
                        display:
                            this.state.status === 'STATUS.INITIAL' ? 'inline' : 'none'
                    }}
                    href={`${STRAVA_BASE_URL}?client_id=${CLIENT_ID}&response_type=code&scope=${SCOPES}&redirect_uri=${redirectBaseUrl}`}
                >
                    Login
                </a>
                {/*<Loading*/}
                {/*    status={this.state.status}*/}
                {/*    callback={() => {*/}
                {/*      if (this.props.status !== STATUS.AUTHENTICATED) {*/}
                {/*        this.setState({*/}
                {/*          status: STATUS.AUTHENTICATED*/}
                {/*        });*/}
                {/*      }*/}
                {/*    }}*/}
                {/*/>*/}
                { this.state.token !== null &&
                <pre>{JSON.stringify(this.state.token, null, '  ')}</pre>
                }
            </div>

        )
            ;
    }
}

const mapStateToProps = ({ authentication }: IRootState) => ({
    isAuthenticated: authentication.isAuthenticated,
    loginError: authentication.loginError,
    showModal: authentication.showModalLogin
});

const mapDispatchToProps = { login };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Login);
