import React from 'react';
import { AvDateRangeField } from '@availity/reactstrap-validation-date';
import { AvForm, AvField } from 'availity-reactstrap-validation';
import { connect } from 'react-redux';
import { Row, Col, Button, FormGroup, Container } from 'reactstrap';
import axios from 'axios';

import { IRootState } from 'app/shared/reducers';
import { getSession } from 'app/shared/reducers/authentication';

export interface IStravaProp extends StateProps, DispatchProps {
    account: any;
}

interface ICloneActivity {
    result: any;
    loading?: boolean;
}

export class Clone extends React.Component<IStravaProp, ICloneActivity> {

    constructor(props: IStravaProp, context: any) {
        super(props, context);
        this.state = {
            result: null
        };
    }

    clone = (event, errors, values) => {
      axios.post('/cli/activities', values)
          .then(respone => respone.data)
          .then(data => this.setState({ result: data }));
    };

    render() {
    const { result } = this.state;
    return (
      <>
      <Row>
          <Col md="12">
              <AvForm onSubmit={this.clone}>
                  <AvField
                      name="activityId"
                      label="Activity ID"
                      placeholder="123"
                      required
                      errorMessage="Activity ID cannot be empty"
                      autoFocus
                  />
                  <AvDateRangeField
                      name="range"
                      label="Date range"
                      start={{ name: 'from', label: 'From' }}
                      end={{ name: 'to' }}
                      ranges={{
                          'Last 5 Days': {
                              startDate: now => now.add(-5, 'd'),
                              endDate: now => now
                          },
                          'Yesterday': {
                              startDate: now => now.add(-1, 'd'),
                              endDate: now => now.add(-1, 'd')
                          }
                      }}
                      required
                  />
                  <FormGroup>
                    <Button color="primary">Clone</Button>
                  </FormGroup>
              </AvForm>
              { result &&
              <Container class="text-left">
                  <span>Result:</span>
                  <pre>{JSON.stringify(result, null, '  ')}</pre>
              </Container>
              }
          </Col>
      </Row>
      </>
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
)(Clone);
