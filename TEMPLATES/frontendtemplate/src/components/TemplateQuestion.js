import React, { useState, useEffect } from "react";
import { 
  Container, Typography, Divider, TextField, Button, Box, 
  Radio, RadioGroup, FormControlLabel, Paper, IconButton, 
  List, ListItem, ListItemText 
} from "@mui/material";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import RemoveCircleOutlineIcon from '@mui/icons-material/RemoveCircleOutline';
import { addQuestionsBulk } from "../services/api";
import { useNavigate, useLocation } from "react-router-dom";

const TemplateQuestion = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const { templateId, templateName } = location.state || {};

  const [questions, setQuestions] = useState([
    { questionName: "", defaultAnswer: true }
  ]);
  const [savedQuestions, setSavedQuestions] = useState([]);

  useEffect(() => {
    if (!templateId) {
      navigate('/');
    }
  }, [templateId, navigate]);

  const handleQuestionChange = (index, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index].questionName = value;
    setQuestions(updatedQuestions);
  };

  const handleAnswerChange = (index, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index].defaultAnswer = value === "true";
    setQuestions(updatedQuestions);
  };

  const addNewQuestion = () => {
    setQuestions([...questions, { questionName: "", defaultAnswer: true }]);
  };

  const removeQuestion = (index) => {
    const updatedQuestions = questions.filter((_, i) => i !== index);
    setQuestions(updatedQuestions);
  };

  const handleAddQuestions = async () => {
    try {
      const filteredQuestions = questions.filter(q => q.questionName.trim());
      if (filteredQuestions.length === 0) {
        alert("Please add at least one valid question.");
        return;
      }

      // Prepare data for bulk API
      const bulkData = filteredQuestions.map(q => ({
        questionName: q.questionName,
        status: "ACTIVE",
        createdBy: "admin",
        template: { id: templateId },
        defaultAnswer: { defaultAnswer: q.defaultAnswer }
      }));

      // Send bulk request
      const res = await addQuestionsBulk(bulkData);

      // Update saved questions
      setSavedQuestions([...savedQuestions, ...res.data]);

      // Clear input fields
      setQuestions([{ questionName: "", defaultAnswer: true }]);
      
    } catch (error) {
      console.error("Error adding questions:", error);
      alert("Error adding questions. Please try again.");
    }
  };

  const handleSubmit = () => {
    navigate('/view-template');
  };

  const handleBackToMain = () => {
    navigate('/');
  };

  return (
    <Container sx={{ maxWidth: "800px", margin: "auto", paddingTop: 3, paddingBottom: 5 }}>
      <Box sx={{ display: "flex", alignItems: "center", justifyContent: "space-between" }}>
        <Typography variant="h5">
          Add Questions to Template: {templateName}
        </Typography>
        <IconButton color="primary" onClick={addNewQuestion}>
          <AddCircleOutlineIcon />
        </IconButton>
      </Box>
      
      <Divider sx={{ marginBottom: 3 }} />

      {savedQuestions.length > 0 && (
        <Paper sx={{ padding: 2, marginBottom: 3 }}>
          <Typography variant="h6" sx={{ fontSize: "16px", marginBottom: 2 }}>
            Added Questions
          </Typography>
          <List>
            {savedQuestions.map((q, index) => (
              <ListItem key={index} sx={{ borderBottom: index < savedQuestions.length - 1 ? "1px solid #eee" : "none" }}>
                <ListItemText 
                  primary={q.questionName}
                  secondary={q.defaultAnswer?.defaultAnswer ? "Default: Yes" : "Default: No"}
                />
              </ListItem>
            ))}
          </List>
        </Paper>
      )}

      <Paper sx={{ padding: 3, marginBottom: 3 }}>
        <Typography variant="h6" sx={{ fontSize: "16px", marginBottom: 2 }}>
          Add New Questions
        </Typography>

        {questions.map((question, index) => (
          <Box key={index} sx={{ 
            display: "flex", 
            alignItems: "center", 
            marginBottom: 2,
            padding: 2,
            border: "1px solid #eee",
            borderRadius: 1
          }}>
            <Box sx={{ flexGrow: 1 }}>
              <TextField
                label="Question Name"
                value={question.questionName}
                onChange={(e) => handleQuestionChange(index, e.target.value)}
                fullWidth
                size="small"
                sx={{ marginBottom: 1 }}
              />
              <RadioGroup
                row
                value={question.defaultAnswer}
                onChange={(e) => handleAnswerChange(index, e.target.value)}
              >
                <FormControlLabel value={true} control={<Radio />} label="Yes" />
                <FormControlLabel value={false} control={<Radio />} label="No" />
              </RadioGroup>
            </Box>
            <IconButton color="secondary" onClick={() => removeQuestion(index)} sx={{ ml: 1 }}>
              <RemoveCircleOutlineIcon />
            </IconButton>
          </Box>
        ))}

        <Box sx={{ mt: 3, display: "flex", justifyContent: "flex-end" }}>
          <Button 
            variant="contained" 
            color="primary" 
            onClick={handleAddQuestions}
          >
            Add Questions
          </Button>
        </Box>
      </Paper>

      <Button 
        variant="contained" 
        color="success" 
        onClick={handleSubmit}
        disabled={savedQuestions.length === 0}
        sx={{ marginTop: 2 }}
      >
        Submit Template
      </Button>

      <Button 
        variant="outlined" 
        onClick={handleBackToMain}
        sx={{ marginTop: 2, marginLeft: 2 }}
      >
        Back to Main Page
      </Button>
    </Container>
  );
};

export default TemplateQuestion;
