import './home.scss';

import React from 'react';
import { Redirect, Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';
import Athlete from 'app/modules/strava/athlete';

export interface IHomeProp extends StateProps, DispatchProps {}

const REGEXP = /(?:\?|&)code=([^&]+)/;

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    this.props.getSession();
  }

  render() {
    const { account } = this.props;
    return (
      <Row>
        <Col md="9">
          <h2>Welcome to <b>strava-cli</b>!</h2>
          <p className="lead">Homepage</p>
          {account && account.athlete ? (
            <div>
              <Alert color="success">You are logged in as user {account.athlete.username}.</Alert>
                <Athlete/>
            </div>
          ) : (
            <div>
              <Alert color="warning">
                If you want to use <b>strava-cli</b> login with strava using menu button.
              </Alert>
            </div>
          )}
        </Col>
        <Col md="3" className="pad">
          <span className="hipster rounded" />
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
