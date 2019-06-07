import React from 'react';
import { Redirect, Link } from 'react-router-dom';

import { connect } from 'react-redux';
import { Row, Col, Alert } from 'reactstrap';
import axios from 'axios';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';

export interface IStravaProp extends StateProps, DispatchProps {
    account: any;
}

export class Athlete extends React.Component<IStravaProp> {

    constructor(props: IStravaProp, context: any) {
        super(props, context);
    }

    render() {
    const { account } = this.props;
    return (
      <Row>
        <Col md="12">
          { account.athlete &&
          <>
          <span>Strava account data:</span>
          <pre>{JSON.stringify(account.athlete, null, '  ')}</pre>
          </>
          }
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = storeState => ({
    isAuthenticated: storeState.authentication.isAuthenticated,
    account: storeState.authentication.account
});

const mapDispatchToProps = { getSession };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Athlete);
