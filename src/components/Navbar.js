import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <nav>
      <Link to="/persons">Persons</Link> |{" "}
      <Link to="/person-types">Person types</Link> |{" "}
      <Link to="/parts">Parts</Link> |{" "}
      <Link to="/categories">Categories</Link> |{" "}
      <Link to="/orders">Orders</Link>
    </nav>
  );
};

export default Navbar;
