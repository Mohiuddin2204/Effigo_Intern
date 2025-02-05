import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { Table, Button } from "react-bootstrap";
import { toast } from "react-toastify";

const CityList = () => {
  const [cities, setCities] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:1122/countries/cities")
      .then((response) => setCities(response.data))
      .catch((error) => toast.error("Error fetching cities"));
  }, []);

  const deleteCity = (cityId) => {
    axios.delete(`http://localhost:1122/countries/cities/${cityId}`)

      .then(() => {
        setCities(cities.filter((city) => city.cityId !== cityId));
        toast.success("City deleted successfully");
      })
      .catch(() => toast.error("Error deleting city"));
  };

  return (
    <div>
      <h2>Cities</h2>
      <Link to="/cities/add">
        <Button variant="primary" className="mb-3">
          Add City
        </Button>
      </Link>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>City Name</th>
            <th>Country</th>
            <th>Population</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {cities.map((city) => (
            <tr key={city.cityId}>
              <td>{city.cityName}</td>
              <td>{city.country?.countryName}</td>
              <td>{city.population}</td>
              <td>
              <Link to={`/cities/edit/${city.cityId}`}>

                  <Button variant="warning" className="me-2">
                    Edit
                  </Button>
                </Link>
                <Button
                  variant="danger"
                  onClick={() => deleteCity(city.cityId)}
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

export default CityList;