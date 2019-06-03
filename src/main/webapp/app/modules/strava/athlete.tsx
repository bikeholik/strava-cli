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

export interface IStravaProp2 {
    account: any;
}

export class Athlete extends React.Component<IStravaProp, IStravaProp2> {

    constructor(props: IStravaProp, context: any) {
        super(props, context);
        this.setState({});
    }

    componentDidMount(): void {
      axios.get('/api/test')
          .then(respone => respone.data)
          .then(data => this.setState({ account: data }))
          .catch( error => this.setState({
              account: {
                  error,
                  message: 'from code'
              }
          }));
  }

    render() {
    const { account } = this.state;
    return (
      <Row>
        <Col md="12">
          { account &&
          <pre>{JSON.stringify(account, null, '  ')}</pre>
          }
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
)(Athlete);
