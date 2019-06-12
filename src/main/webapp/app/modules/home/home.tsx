import './home.scss';

import React from 'react';
import {Link} from 'react-router-dom';

import {connect} from 'react-redux';
import {Alert, Col, Row} from 'reactstrap';
import {getSession} from 'app/shared/reducers/authentication';
import Athlete from 'app/modules/strava/athlete';

export interface IHomeProp extends StateProps, DispatchProps {}

const REGEXP = /(?:\?|&)code=([^&]+)/;

export class Home extends React.Component<IHomeProp> {
  componentDidMount() {
    // this.props.getSession();
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
                  <div className="d-flex">
                      <Alert color="success" className="mr-auto p-4">You are logged in as
                          user {account.athlete.username ? account.athlete.username : account.athlete.firstname + ' ' + account.athlete.lastname}.</Alert>
                      <div className="p-2">Not you? <Link to="/logout">Logout</Link></div>
                  </div>
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
