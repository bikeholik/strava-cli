import React from 'react';
import { connect } from 'react-redux';
import { Redirect, RouteComponentProps } from 'react-router-dom';

import { IRootState } from 'app/shared/reducers';
import { login } from 'app/shared/reducers/authentication';

export interface IRouter {
    code: string;
}

export interface ILoginProps extends StateProps, DispatchProps, RouteComponentProps<IRouter> {
}

export interface ILoginState {
    showModal: boolean;
    code: string;
    status: string;
}

const REGEXP = /(?:\?|&)code=([^&]+)/;

export class Login extends React.Component<ILoginProps, ILoginState> {
    state: ILoginState = {
        showModal: this.props.showModal,
        code: window.location.href.match(REGEXP) ? window.location.href.match(REGEXP)[1] : this.props.match.params.code,
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

    componentDidMount() {
        const code = this.state.code;
        if (code) {
            this.handleLogin(code, true);
        }
    }

    render() {
        if (this.state.status === 'STATUS.AUTHENTICATED' || this.props.isAuthenticated || !this.state.code) {
            return (
                <Redirect to={{
                    pathname: '/',
                    search: ''
                }}
                />
                );
        }
        return (
            <div className="app-loading">
                <div className="lds-css ng-scope">
                    <div className="lds-pacman">
                        <div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div></div>
                        <div><div>&nbsp;</div><div>&nbsp;</div><div>&nbsp;</div></div>
                    </div>
                </div>
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
