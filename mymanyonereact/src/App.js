import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Container } from "react-bootstrap";
import CityList from "./components/CityList";
import CityForm from "./components/CityForm";
import CountryList from "./components/CountryList";
import CountryForm from "./components/CountryForm";
import HomePage from "./components/HomePage"; // Import HomePage component
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <Router>
      <Container>
        <h1 className="my-4">City & Country Management</h1>
        <ToastContainer />
        <Routes>
          {/* Set HomePage as the default route */}
          <Route path="/" element={<HomePage />} />
          <Route path="/cities" element={<CityList />} />
          <Route path="/cities/add" element={<CityForm />} />
          <Route path="/cities/edit/:cityId" element={<CityForm />} />
          <Route path="/countries" element={<CountryList />} />
          <Route path="/countries/add" element={<CountryForm />} />
          <Route path="/countries/edit/:countryId" element={<CountryForm />} />
        </Routes>
      </Container>
    </Router>
  );
}

export default App;