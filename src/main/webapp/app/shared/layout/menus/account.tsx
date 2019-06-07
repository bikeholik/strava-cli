import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavLink as Link } from 'react-router-dom';

import { NavDropdown } from './menu-components';

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID;
const STRAVA_BASE_URL = 'https://www.strava.com/oauth/authorize';
const SCOPES = 'read,activity:read,activity:write';

const CONNECT_URL = `${STRAVA_BASE_URL}?client_id=${CLIENT_ID}&response_type=code&scope=${SCOPES}&redirect_uri=${location.origin}/login`;

const accountMenuItemsAuthenticated = (
  <>
    <MenuItem icon="sign-out-alt" to="/logout">
      Sign out
    </MenuItem>
  </>
);

const accountMenuItems = (
  <>
      <MenuItem id="login-item" to={CONNECT_URL} external=true>
          <img src="/content/images/btn_strava_connectwith_orange.svg" alt="Connect with Strava"/>
      </MenuItem>
  </>
);

export const AccountMenu = ({ isAuthenticated = false }) => (
  <NavDropdown icon="user" name="Account" id="account-menu">
    {isAuthenticated ? accountMenuItemsAuthenticated : accountMenuItems}
  </NavDropdown>
);

export default AccountMenu;
