import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import { toast } from "react-toastify";

const CountryForm = () => {
  const [country, setCountry] = useState({
    countryName: "",
    currency: "",
    population: 0,
    latitude: "",
    longitude: "",
  });
  const navigate = useNavigate();
  const { countryId } = useParams();

  useEffect(() => {
    if (countryId) {
      axios
        .get(`http://localhost:1122/countries/${countryId}`)
        .then((response) => setCountry(response.data))
        .catch(() => toast.error("Error fetching country"));
    }
  }, [countryId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCountry({ ...country, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const method = countryId ? axios.put : axios.post;
    const url = countryId
      ? `http://localhost:1122/countries/${countryId}`
      : "http://localhost:1122/countries";

    method(url, country)
      .then(() => {
        toast.success(`Country ${countryId ? "updated" : "added"} successfully`);
        navigate("/countries");
      })
      .catch(() => toast.error("Error saving country"));
  };

  return (
    <Container>
      <Row className="justify-content-center mt-5">
        <Col md={6}>
          <div className="text-center mb-4">
            <h2>{countryId ? "Edit Country" : "Add Country"}</h2>
          </div>
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3">
              <Form.Label>Country Name</Form.Label>
              <Form.Control
                type="text"
                name="countryName"
                value={country.countryName}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Currency</Form.Label>
              <Form.Control
                type="text"
                name="currency"
                value={country.currency}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Population</Form.Label>
              <Form.Control
                type="number"
                name="population"
                value={country.population}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Latitude</Form.Label>
              <Form.Control
                type="text"
                name="latitude"
                value={country.latitude}
                onChange={handleInputChange}
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>Longitude</Form.Label>
              <Form.Control
                type="text"
                name="longitude"
                value={country.longitude}
                onChange={handleInputChange}
              />
            </Form.Group>

            <div className="text-center">
              <Button variant="primary" type="submit">
                {countryId ? "Update Country" : "Add Country"}
              </Button>
            </div>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default CountryForm;