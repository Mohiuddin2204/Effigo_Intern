import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import { Form, Button } from "react-bootstrap";
import { toast } from "react-toastify";

const CityForm = () => {
  const [city, setCity] = useState({
    cityName: "",
    population: 0,
    latitude: "",
    longitude: "",
    country: { countryId: "" }, // Ensure country is always an object
  });

  const [countries, setCountries] = useState([]);
  const navigate = useNavigate();
  const { cityId } = useParams();

  useEffect(() => {
    axios.get("http://localhost:8080/countries")
      .then((response) => setCountries(response.data))
      .catch(() => toast.error("Error fetching countries"));

    if (cityId) {
      axios.get(http://localhost:8080/countries/cities/${cityId})
        .then((response) => {
          const cityData = response.data;
          setCity({
            ...cityData,
            country: cityData.country ? cityData.country : { countryId: "" }, // Ensure country object
          });
        })
        .catch(() => toast.error("Error fetching city"));
    }
  }, [cityId]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setCity((prevCity) => ({ ...prevCity, [name]: value }));
  };

  const handleCountryChange = (e) => {
    setCity((prevCity) => ({
      ...prevCity,
      country: { countryId: e.target.value || "" }, // Ensure valid countryId
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const method = cityId ? axios.put : axios.post;
    const url = cityId
      ? http://localhost:8080/countries/cities/${cityId}
      : "http://localhost:8080/countries/cities";

    method(url, city)
      .then(() => {
        toast.success(City ${cityId ? "updated" : "added"} successfully);
        navigate("/cities");
      })
      .catch(() => toast.error("Error saving city"));
  };

  return (
    <div>
      <h2>{cityId ? "Edit City" : "Add City"}</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>City Name</Form.Label>
          <Form.Control
            type="text"
            name="cityName"
            value={city.cityName}
            onChange={handleInputChange}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Country</Form.Label>
          <Form.Control
            as="select"
            name="country"
            value={city.country?.countryId || ""}
            onChange={handleCountryChange}
            required
          >
            <option value="">Select a country</option>
            {countries.map((country) => (
              <option key={country.countryId} value={country.countryId}>
                {country.countryName}
              </option>
            ))}
          </Form.Control>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Population</Form.Label>
          <Form.Control
            type="number"
            name="population"
            value={city.population}
            onChange={handleInputChange}
            required
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Latitude</Form.Label>
          <Form.Control
            type="text"
            name="latitude"
            value={city.latitude}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Longitude</Form.Label>
          <Form.Control
            type="text"
            name="longitude"
            value={city.longitude}
            onChange={handleInputChange}
          />
        </Form.Group>

        <Button variant="primary" type="submit">
          {cityId ? "Update City" : "Add City"}
        </Button>
      </Form>
    </div>
  );
};

export default CityForm;
