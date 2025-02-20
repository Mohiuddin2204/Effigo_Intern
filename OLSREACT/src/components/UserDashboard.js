import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import axios from 'axios';
import { Button, Container, Row, Col, Card, CardBody } from 'reactstrap';
import { Link } from 'react-router-dom';
import { FaBook, FaTags, FaShoppingCart, FaMoneyBill, FaPlus, FaMoneyCheckAlt } from 'react-icons/fa';
import '../styles/UserDashboard.css'; // Import custom styles

const UserDashboard = () => {
  const { authState } = useContext(AuthContext);
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUserDetails = async () => {
      try {
        const userName = authState.username;
        if (!userName) throw new Error("Username is not available");

        const token = localStorage.getItem('jwtToken');
        if (!token) throw new Error("Token is missing. Please log in again.");

        const response = await axios.get(
          `http://localhost:8082/auth/user/api/users/username/${userName}`,
          { headers: { Authorization: `Bearer ${token}` } }
        );
        setUser(response.data);
      } catch (error) {
        console.error("Error fetching user details!", error);
        setError(error.response?.data || "Error fetching user details.");
      }
    };

    fetchUserDetails();
  }, [authState.username]);

  if (error) {
    return <div className="error-message">Error: {error}</div>;
  }

  return (
    <div className="user-dashboard">
      <Container className="dashboard-container">
        <Row>
          <Col md={12} className="text-center">
            {user ? (
              <h2 className="welcome-text">Welcome, {user.userName} 👋</h2>
            ) : (
              <h2 className="loading-text">Loading user details...</h2>
            )}
          </Col>
        </Row>

        <Row className="dashboard-options">
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaBook className="dashboard-icon" />
                <h5>View Courses</h5>
                <Link to="/user/courses">
                  <Button color="primary" block>Go to Courses</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>

          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaTags className="dashboard-icon" />
                <h5>View Categories</h5>
                <Link to="/user/categories">
                  <Button color="secondary" block>Go to Categories</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>

          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaShoppingCart className="dashboard-icon" />
                <h5>View My Orders</h5>
                <Link to="/user/orders">
                  <Button color="success" block>Go to Orders</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
        </Row>

        <Row className="dashboard-options">
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaMoneyBill className="dashboard-icon" />
                <h5>View My Payments</h5>
                <Link to="/user/payments">
                  <Button color="warning" block>Go to Payments</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>

          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaPlus className="dashboard-icon" />
                <h5>Place Order!!!</h5>
                <Link to="/user/orders/add">
                  <Button color="info" block>Place Order</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>

         {/* Add Payment / Process Section */}
<Col md={4} className="mb-4">
  <Card className="dashboard-card">
    <CardBody className="text-center">
      <FaMoneyCheckAlt className="dashboard-icon" />
      <h5>Add Payment / Process</h5>
      <Link to="/user/payments/add">
        <Button color="primary" block>Add Payment</Button>
      </Link>
    </CardBody>
  </Card>
</Col>
        </Row>
      </Container>
    </div>
  );
};

export default UserDashboard;
