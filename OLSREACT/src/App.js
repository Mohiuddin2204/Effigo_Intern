import React, { useContext, useEffect } from "react"; 
import { Route, Routes, Navigate, useNavigate } from "react-router-dom"; 
import { ToastContainer } from "react-toastify"; 
import "./App.css";


import Login from "./components/Auth/Login";
import Register from "./components/Auth/Register";
import UserDashboard from "./components/UserDashboard";
import AdminDashboard from "./components/adminDashboard";
import UserList from "./components/Users/UserList";
import OrderList from "./components/Orders/OrderList";
import PaymentList from "./components/payments/PaymentList";
import CategoryList from "./components/categories/CategoryList"; 
import CourseList from "./components/Courses/CourseList";
import AddCategory from "./components/categories/AddCategory";
import DeleteCategory from "./components/categories/DeleteCategory"; 
import AddCourse from "./components/Courses/AddCourse"; 
import { AuthContext } from "./context/AuthContext";
import UserCategoryList from "./components/categories/UserCategoryList";
import UserCourseList from "./components/Courses/UserCourseList";
import UserOrderList from "./components/Orders/UserOrderList";
import UserPaymentList from "./components/payments/UserPaymentList";
import AddOrder from "./components/Orders/AddOrder";
import AddPayment from "./components/payments/AddPayment";

const ProtectedRoute = ({ children, role }) => {
  const navigate = useNavigate();
  const { authState } = useContext(AuthContext);

  useEffect(() => {
    if (authState.isAuthenticated) {
      if (role === "ROLE_ADMIN" && authState.roles === "ROLE_ADMIN") {
        navigate("/AdminDashboard");
      } else if (role === "ROLE_USER" && authState.roles === "ROLE_USER") {
        navigate("/UserDashboard");
      }
    } else {
      navigate("/login");
    }
  }, [authState.isAuthenticated, authState.roles, navigate]);

  if (!authState.isAuthenticated) {
    return <p>Loading...</p>;
  }

  return children;
};

const App = () => {
  return (
    <div className="App">
      <ToastContainer />
      <Routes>
      <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        {/* Protected Routes */}
        <Route path="/UserDashboard" element={<ProtectedRoute role="ROLE_USER"><UserDashboard /></ProtectedRoute>} />
        <Route path="/AdminDashboard" element={<ProtectedRoute role="ROLE_ADMIN"><AdminDashboard /></ProtectedRoute>} />

        {/* Admin Routes */}
        <Route path="/admin/users" element={<ProtectedRoute role="ROLE_ADMIN"><UserList /></ProtectedRoute>} />
        <Route path="/admin/orders" element={<ProtectedRoute role="ROLE_ADMIN"><OrderList /></ProtectedRoute>} />
        <Route path="/admin/payments" element={<ProtectedRoute role="ROLE_ADMIN"><PaymentList /></ProtectedRoute>} />
        <Route path="/admin/categories" element={<ProtectedRoute role="ROLE_ADMIN"><CategoryList /></ProtectedRoute>} />
        <Route path="/admin/categories/add" element={<ProtectedRoute role="ROLE_ADMIN"><AddCategory /></ProtectedRoute>} />
        <Route path="/admin/categories/delete" element={<ProtectedRoute role="ROLE_ADMIN"><DeleteCategory /></ProtectedRoute>} />
        <Route path="/admin/courses" element={<ProtectedRoute role="ROLE_ADMIN"><CourseList /></ProtectedRoute>} />
        <Route path="/admin/courses/add" element={<ProtectedRoute role="ROLE_ADMIN"><AddCourse /></ProtectedRoute>} />

        {/* User Routes */}
        <Route path="/user/categories" element={<ProtectedRoute role="ROLE_USER"><UserCategoryList /></ProtectedRoute>} />
        <Route path="/user/courses" element={<ProtectedRoute role="ROLE_USER"><UserCourseList /></ProtectedRoute>} />
        <Route path="/user/orders" element={<ProtectedRoute role="ROLE_USER"><UserOrderList /></ProtectedRoute>} />
        <Route path="/user/payments" element={<ProtectedRoute role="ROLE_USER"><UserPaymentList /></ProtectedRoute>} />
        <Route path="/user/orders/add" element={<ProtectedRoute role="ROLE_USER"><AddOrder /></ProtectedRoute>} />
        <Route path="/user/payments/add" element={<ProtectedRoute role="ROLE_USER"><AddPayment /></ProtectedRoute>} />
      </Routes>
    </div>
  );
};

export default App;
