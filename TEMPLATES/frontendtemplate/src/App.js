import React, { useState } from "react";
import { Container, Typography, Divider, Button, Box, Paper } from "@mui/material";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import TemplateForm from "./components/TemplateForm";
import QuestionSection from "./components/QuestionSection";
import ViewTemplatePage from "./components/ViewTemplatePage";
import TemplateQuestion from "./components/TemplateQuestion";

const MainPage = () => {
  const [selectedTemplate, setSelectedTemplate] = useState(null);

  return (
    <Container sx={{ maxWidth: "800px", margin: "auto", paddingTop: 3 }}>

      {/* Stylish Header for "Templates" */}
      <Paper
        elevation={3}
        sx={{
          backgroundColor: "#E3F2FD",
          padding: 2,
          borderRadius: 2,
          textAlign: "left", // Aligned to Left
          marginBottom: 3,
          boxShadow: "0px 3px 6px rgba(0, 0, 0, 0.1)",
        }}
      >
        <Typography variant="h5" sx={{ fontWeight: "bold", color: "#000" }}>
          Templates
        </Typography>
      </Paper>

      <TemplateForm onTemplateSelect={setSelectedTemplate} />

      {selectedTemplate && <QuestionSection templateId={selectedTemplate} />}

      <Box sx={{ marginTop: 4, textAlign: "left" }}> {/* Left aligned */}
        <Button
          component={Link}
          to="/view-template"
          variant="contained"
          sx={{
            backgroundColor: "#1976D2",
            "&:hover": { backgroundColor: "#115293" },
            fontWeight: "bold",
            padding: "10px 20px",
            fontSize: "16px",
          }}
        >
          View Template
        </Button>
      </Box>
    </Container>
  );
};

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/view-template" element={<ViewTemplatePage />} />
        <Route path="/template-question" element={<TemplateQuestion />} />
      </Routes>
    </Router>
  );
};

export default App;
