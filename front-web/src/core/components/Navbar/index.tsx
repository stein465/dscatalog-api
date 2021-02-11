import React from 'react';
import './styles.scss';
import {Link} from 'react-router-dom';

const Navbar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <Link to="/" className="nav-logo-text" ><h4>DS Catalog</h4></Link>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <Link to="/" className="active">HOME</Link>
                </li>
                <li>
                    <Link to="/catalog" >CATÁLOGO</Link>
                </li>
                <li>
                    <Link to="/admin">ADMIN</Link>
                </li>
            </ul>
        </div>
    </nav>
)




export default Navbar;