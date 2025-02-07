import React from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <Container className="text-center">
      <Row>
        <Col>
          <h1>Welcome to the Book Management App</h1>
          <p>This app allows you to manage books, authors, and publishers.</p>
          <div>
            <Link to="/books">
              <Button variant="primary" className="m-2">View Books</Button>
            </Link>
            <Link to="/authors">
              <Button variant="secondary" className="m-2">View Authors</Button>
            </Link>
            <Link to="/publishers">
              <Button variant="success" className="m-2">View Publishers</Button>
            </Link>
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default Home;
