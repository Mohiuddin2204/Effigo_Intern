// src/components/HomePage.js
import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const HomePage = () => {
  return (
    <div className="text-center mt-5">
      <h2>Welcome to City & Country Management</h2>
      <div className="mt-4">
        <Link to="/cities">
          <Button variant="primary" className="me-3">
            Manage Cities
          </Button>
        </Link>
        <Link to="/countries">
          <Button variant="secondary">
            Manage Countries
          </Button>
        </Link>
      </div>
    </div>
  );
};

export default HomePage;