import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { Table, Button } from "react-bootstrap";
import { toast } from "react-toastify";

const CountryList = () => {
  const [countries, setCountries] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:1122/countries")
      .then((response) => setCountries(response.data))
      .catch((error) => toast.error("Error fetching countries"));
  }, []);

  const deleteCountry = (countryId) => {
    axios
      .delete(`http://localhost:1122/countries/${countryId}`)
      .then(() => {
        setCountries(countries.filter((country) => country.countryId !== countryId));
        toast.success("Country deleted successfully");
      })
      .catch(() => toast.error("Error deleting country"));
  };

  return (
    <div>
      <h2>Countries</h2>
      <Link to="/countries/add">
        <Button variant="primary" className="mb-3">
          Add Country
        </Button>
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Country Name</th>
            <th>Currency</th>
            <th>Population</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {countries.map((country) => (
            <tr key={country.countryId}>
              <td>{country.countryName}</td>
              <td>{country.currency}</td>
              <td>{country.population}</td>
              <td>
                <Link to={`/countries/edit/${country.countryId}`}>
                  <Button variant="warning" className="me-2">
                    Edit
                  </Button>
                </Link>
                <Button
                  variant="danger"
                  onClick={() => deleteCountry(country.countryId)}
                >
                  Delete
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default CountryList;