import React from 'react';
import { DropdownItem } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconProp } from '@fortawesome/fontawesome-svg-core';

export interface IMenuItem {
  icon: IconProp;
  to: string;
  id?: string;
  external?: boolean;
}

export default class MenuItem extends React.Component<IMenuItem> {
  render() {
    const { to, icon, id, external, children } = this.props;

    return (
        !external ?
      <DropdownItem tag={Link} to={to} id={id}>
        <FontAwesomeIcon icon={icon} fixedWidth /> {children}
      </DropdownItem>
            :
            <DropdownItem tag="a" href={to} id={id}>
              <FontAwesomeIcon icon={icon} fixedWidth /> {children}
            </DropdownItem>
    );
  }
}
