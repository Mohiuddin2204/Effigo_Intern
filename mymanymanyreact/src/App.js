import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import BookList from "./components/BookList";
import AddBook from "./components/AddBook";
import UpdateBook from "./components/UpdateBook";
import AuthorList from "./components/AuthorList";
import AddAuthor from "./components/AddAuthor";
import UpdateAuthor from "./components/UpdateAuthor";
import PublisherList from "./components/PublisherList";
import AddPublisher from "./components/AddPublisher";
import UpdatePublisher from "./components/UpdatePublisher";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Home />} />
          
          {/* Book Routes */}
          <Route path="/books" element={<BookList />} />
          <Route path="/books/add" element={<AddBook />} />
          <Route path="/books/update/:id" element={<UpdateBook />} />
          
          {/* Author Routes */}
          <Route path="/authors" element={<AuthorList />} />
          <Route path="/authors/add" element={<AddAuthor />} />
          <Route path="/authors/update/:id" element={<UpdateAuthor />} />
          
          {/* Publisher Routes */}
          <Route path="/publishers" element={<PublisherList />} />
          <Route path="/publishers/add" element={<AddPublisher />} />
          <Route path="/publishers/update/:id" element={<UpdatePublisher />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
