import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';
import { Button, Container, Row, Col, Card, CardBody } from 'reactstrap'; 
import { FaUsers, FaShoppingCart, FaMoneyBill, FaTags, FaPlus, FaTrash, FaBook } from 'react-icons/fa';

const AdminDashboard = () => {
  const { authState } = useContext(AuthContext);

  return (
    <div className="admin-dashboard">
      <Container className="mt-5 text-center">
        <h2 className="dashboard-title">Welcome, ADMIN {authState?.username} 👋</h2>
        <p className="dashboard-subtitle">Manage users, orders, payments, categories, and courses efficiently.</p>

        <Row className="mt-4">
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaUsers className="dashboard-icon users" />
                <h5>View Users</h5>
                <Link to="/admin/users">
                  <Button color="primary" block>Go to Users</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaShoppingCart className="dashboard-icon orders" />
                <h5>View User Orders</h5>
                <Link to="/admin/orders">
                  <Button color="secondary" block>Go to Orders</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaMoneyBill className="dashboard-icon payments" />
                <h5>View Payments of Users</h5>
                <Link to="/admin/payments">
                  <Button color="success" block>Go to Payments</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaTags className="dashboard-icon categories" />
                <h5>View Categories</h5>
                <Link to="/admin/categories">
                  <Button color="warning" block>Go to Categories</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaPlus className="dashboard-icon add-category" />
                <h5>Add Category</h5>
                <Link to="/admin/categories/add">
                  <Button color="info" block>Add</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaTrash className="dashboard-icon delete-category" />
                <h5>Delete Category</h5>
                <Link to="/admin/categories/delete">
                  <Button color="danger" block>Delete</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
        </Row>

        <Row className="mt-4">
          <Col md={4} className="mb-4">
            <Card className="dashboard-card">
              <CardBody className="text-center">
                <FaBook className="dashboard-icon courses" />
                <h5>Manage Courses</h5>
                <Link to="/admin/courses">
                  <Button color="dark" block>Go to Courses</Button>
                </Link>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default AdminDashboard;
