import React, { useState, useEffect } from "react";
import { 
  Container, 
  Typography, 
  Divider, 
  FormControl, 
  InputLabel, 
  Select, 
  MenuItem, 
  List, 
  ListItem, 
  Button, 
  Box, 
  Paper, 
  Card, 
  CardContent 
} from "@mui/material";
import { fetchTemplates, fetchTemplateQuestions } from "../services/api";
import { useNavigate } from "react-router-dom";

const ViewTemplatePage = () => {
  const [templates, setTemplates] = useState([]);
  const [selectedTemplate, setSelectedTemplate] = useState("");
  const [questions, setQuestions] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const questionsPerPage = 5;
  const navigate = useNavigate();

  useEffect(() => {
    fetchTemplates().then((res) => setTemplates(res.data));
  }, []);

  useEffect(() => {
    if (selectedTemplate) {
      fetchTemplateQuestions(selectedTemplate).then((res) => setQuestions(res.data));
    }
  }, [selectedTemplate]);

  const handleTemplateChange = (event) => {
    setSelectedTemplate(event.target.value);
    setCurrentPage(0);
  };

  const paginatedQuestions = questions.slice(
    currentPage * questionsPerPage,
    (currentPage + 1) * questionsPerPage
  );

  const handleBackToMain = () => {
    navigate('/');
  };

  return (
    <Container 
      sx={{ 
        maxWidth: "900px", 
        margin: "auto", 
        paddingTop: 3, 
        paddingBottom: 5, 
        background: "linear-gradient(to right, #f5f7fa, #c3cfe2)", 
        minHeight: "100vh", 
        borderRadius: 2, 
        boxShadow: "0px 4px 10px rgba(0, 0, 0, 0.1)", 
        padding: 4
      }}
    >
      {/* Header */}
      <Box sx={{ 
        backgroundColor: "#ADD8E6", 
        padding: "15px", 
        borderRadius: "10px", 
        textAlign: "center", 
        marginBottom: "20px"
      }}>
        <Typography variant="h5" sx={{ fontWeight: "bold", color: "#003366" }}>
          View Template
        </Typography>
      </Box>

      <Divider sx={{ marginBottom: 3 }} />

      {/* Template Dropdown */}
      <FormControl fullWidth sx={{ marginBottom: 4, backgroundColor: "white", borderRadius: "5px" }}>
        <InputLabel sx={{ color: "#003366" }}>Select Template</InputLabel>
        <Select
          value={selectedTemplate}
          onChange={handleTemplateChange}
          label="Select Template"
          sx={{
            boxShadow: "0px 2px 5px rgba(0,0,0,0.2)",
            borderRadius: "5px",
          }}
        >
          {templates.map((template) => (
            <MenuItem key={template.id} value={template.id}>
              {template.templateName}
            </MenuItem>
          ))}
        </Select>
      </FormControl>

      {/* Questions Display */}
      {selectedTemplate && (
        <Paper sx={{ padding: 2, marginBottom: 3, borderRadius: "10px", boxShadow: "0px 2px 8px rgba(0,0,0,0.2)" }}>
          <Box sx={{ display: "flex", alignItems: "center", marginBottom: 2 }}>
            <Typography variant="h6" sx={{ fontSize: "18px", fontWeight: "500", color: "#003366" }}>
              {templates.find(t => t.id === selectedTemplate)?.templateName}
            </Typography>
          </Box>

          <List>
            {paginatedQuestions.map((q, index) => (
              <Card 
                key={q.id} 
                sx={{ 
                  marginBottom: 2, 
                  borderRadius: "8px", 
                  boxShadow: "0px 2px 5px rgba(0,0,0,0.2)"
                }}
              >
                <CardContent>
                  <Typography sx={{ fontSize: "16px", fontWeight: "bold", color: "#333" }}>
                    {`${(currentPage * questionsPerPage) + index + 1}. ${q.questionName}`}
                  </Typography>
                  <Typography sx={{ 
                    marginTop: 1, 
                    color: q.defaultAnswer?.defaultAnswer ? "#2196f3" : "#FF5733", 
                    fontWeight: "500" 
                  }}>
                    {q.defaultAnswer?.defaultAnswer ? "Yes" : "No"}
                  </Typography>
                </CardContent>
              </Card>
            ))}
          </List>

          {/* Pagination Buttons */}
          {questions.length > 0 && (
            <Box sx={{ display: "flex", justifyContent: "space-between", marginTop: 2 }}>
              <Button 
                variant="contained" 
                sx={{ backgroundColor: "#003366", color: "white" }}
                disabled={currentPage === 0} 
                onClick={() => setCurrentPage((prev) => prev - 1)}
              >
                Previous
              </Button>
              <Button
                variant="contained"
                sx={{ backgroundColor: "#003366", color: "white" }}
                disabled={(currentPage + 1) * questionsPerPage >= questions.length}
                onClick={() => setCurrentPage((prev) => prev + 1)}
              >
                Next
              </Button>
            </Box>
          )}
        </Paper>
      )}

      {/* Back Button */}
      <Button 
        variant="contained" 
        onClick={handleBackToMain}
        sx={{ 
          marginTop: 2, 
          backgroundColor: "#003366", 
          color: "white", 
          borderRadius: "5px",
          '&:hover': { backgroundColor: "#002244" }
        }}
      >
        Back to Main Page
      </Button>
    </Container>
  );
};

export default ViewTemplatePage;
