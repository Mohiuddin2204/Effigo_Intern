/* eslint-disable no-unused-vars */
import logo from './logo.svg';
import './App.css';
import {Button} from 'reactstrap';
import {ToastContainer,toast} from 'react-toastify';
import Home from './components/Home';
import Course from './components/Course';
import Allcourses from './components/Allcourses';
import AddCourse from './components/AddCourse';
import {Input,Container,Col,Row} from 'reactstrap';
import Header from './components/Header';
import Menus from './components/Menus';
import { BrowserRouter as Router ,Routes,Route} from 'react-router-dom';
function App() {
  const btnHandle=()=>{
    toast.success("first msg",{
      position:"top-center",
  });
  }
  return (
    <div>
      <Router>
        <ToastContainer />
        <Container>
          <Header />
          <Row>
            <Col md={4}>
              <Menus />
            </Col>
            <Col md={8}>
              {/* 🔹 Wrap routes inside `<Routes>` */}
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/add-course" element={<AddCourse />} />
                <Route path="/view-courses" element={<Allcourses />} />
              </Routes>
            </Col>
          </Row>
        </Container>
      </Router>
</div>
  );
}

export default App;
