import React from 'react';
import { Switch } from 'react-router-dom';
import Loadable from 'react-loadable';

import Login from 'app/modules/login/login';
import Logout from 'app/modules/login/logout';
import Home from 'app/modules/home/home';
import PrivateRoute from 'app/shared/auth/private-route';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import PageNotFound from 'app/shared/error/page-not-found';
import Clone from 'app/modules/strava/clone';

// tslint:disable:space-in-parens

const Admin = Loadable({
  loader: () => import(/* webpackChunkName: "administration" */ 'app/modules/administration'),
  loading: () => <div>loading ...</div>
});
// tslint:enable

const Routes = () => (
  <div className="view-routes">
    <Switch>
      <ErrorBoundaryRoute path={ ['/login/:code', '/login'] } component={Login} />
      <ErrorBoundaryRoute path="/logout" component={Logout} />
      <PrivateRoute path="/clone" component={Clone} />
      <ErrorBoundaryRoute path="/" exact component={Home} />
      <ErrorBoundaryRoute component={PageNotFound} />
    </Switch>
  </div>
);

export default Routes;
