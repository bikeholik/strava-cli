import React from 'react';
import {AvDateRangeField} from '@availity/reactstrap-validation-date';
import {AvForm, AvGroup} from 'availity-reactstrap-validation';
import CreatableSelect from 'react-select/creatable';
import {connect} from 'react-redux';
import {Button, Col, Container, FormGroup, Label, Row} from 'reactstrap';
import axios from 'axios';
import {Storage} from 'react-jhipster';
import {getSession} from 'app/shared/reducers/authentication';

export interface IStravaProp extends StateProps, DispatchProps {
    account: any;
}

interface ICloneActivity {
    result: any;
    loading?: boolean;
    options: any;
    value?: any;
}

const SAVED_ACTIVITY_IDS = 'saved-activity-ids';

export class Clone extends React.Component<IStravaProp, ICloneActivity> {

    constructor(props: IStravaProp, context: any) {
        super(props, context);
        this.state = {
            result: null,
            options: Storage.local.get(this.getKey(), [])
        };
    }

    private getKey() {
        return `${SAVED_ACTIVITY_IDS}-${this.props.account.athlete.id}`;
    }

    clone = (event, values) => {
        const requestData = {
            ...values,
            activityId: this.state.value.value
        };
        this.setState({loading: true});
        axios.post('/cli/activities', requestData)
            .then(respone => respone.data)
            .then(data => {
                const opts = this.state.options;
                opts.forEach(v => {
                    if (v.value === requestData.activityId) {
                        v.label = data.clonedActivity.name + ' (' + requestData.activityId + ')';
                    }
                });
                Storage.local.set(this.getKey(), opts);
                return this.setState({result: data, loading: false, options: opts});
            })
            .catch(error => {
                this.setState({result: error.response, loading: false});
            });
    };

    handleChange = (newValue: any, actionMeta: any) => {
        this.setState({value: newValue});
    };

    handleCreate = (inputValue: any) => {
        const {options} = this.state;
        const newOption = {
            label: 'Activity ' + inputValue,
            value: inputValue
        };
        this.setState({
            options: [...options, newOption],
            value: newOption
        });
    };

    private isValidNewOption= (inputValue, selectValue, selectOptions) => inputValue.match(/[0-9]/);

    private formatCreateLabel= (inputValue) => 'Clone activity: ' + inputValue;

    render() {
        const {result, options, value} = this.state;
        return (
            <>
                <Row>
                    <Col md="12">
                        <AvForm onValidSubmit={this.clone}>
                            <AvGroup>
                                <Label for="activityIdSelect">Activity ID</Label>
                                <CreatableSelect
                                    id="activityIdSelect"
                                    isClearable
                                    onChange={this.handleChange}
                                    onCreateOption={this.handleCreate}
                                    options={options}
                                    value={value}
                                    isValidNewOption={this.isValidNewOption}
                                    formatCreateLabel={this.formatCreateLabel}
                                    required
                                />
                            </AvGroup>
                            <AvDateRangeField
                                name="range"
                                label="Date range"
                                start={{name: 'from', label: 'From'}}
                                end={{name: 'to'}}
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
                                <Button color="primary" disabled={this.state.loading}>Clone</Button>
                            </FormGroup>
                        </AvForm>
                        {this.state.loading &&
                        <span>Working...</span>
                        }
                        {result &&
                        <Container className="text-left">
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

const mapDispatchToProps = {getSession};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Clone);
