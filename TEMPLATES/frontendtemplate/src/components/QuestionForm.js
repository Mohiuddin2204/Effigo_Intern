import React, { useState } from "react";
import {
  TextField, Button, Radio, RadioGroup, FormControlLabel, Box, Typography,
  IconButton, Paper
} from "@mui/material";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import { addTemplateQuestionsBulk } from "../services/api";

const QuestionForm = ({ templateId, onQuestionAdded }) => {
  const [questions, setQuestions] = useState([
    { questionName: "", defaultAnswer: true }
  ]);

  // Handle change in question name
  const handleQuestionChange = (index, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index].questionName = value;
    setQuestions(updatedQuestions);
  };

  // Handle change in default answer
  const handleAnswerChange = (index, value) => {
    const updatedQuestions = [...questions];
    updatedQuestions[index].defaultAnswer = value === "true";
    setQuestions(updatedQuestions);
  };

  // Add a new empty question field
  const addNewQuestion = () => {
    setQuestions([...questions, { questionName: "", defaultAnswer: true }]);
  };

  // Remove a question field
  const removeQuestion = (index) => {
    setQuestions(questions.filter((_, i) => i !== index));
  };

  // Submit all questions in bulk
  const handleAddQuestions = async () => {
    try {
      const filteredQuestions = questions.filter(q => q.questionName.trim());
      if (filteredQuestions.length === 0) {
        alert("Please add at least one valid question.");
        return;
      }

      // Prepare bulk API payload
      const bulkData = filteredQuestions.map(q => ({
        questionName: q.questionName,
        status: "active",
        createdBy: "admin",
        defaultAnswer: {
          defaultAnswer: q.defaultAnswer,
          status: "active",
          createdBy: "admin"
        }
      }));

      // Send request
      await addTemplateQuestionsBulk(templateId, bulkData);

      // Reset the form
      setQuestions([{ questionName: "", defaultAnswer: true }]);
      onQuestionAdded(); // Refresh the question list
    } catch (error) {
      console.error("Error adding questions:", error);
      alert("Error adding questions. Please try again.");
    }
  };

  return (
    <Box sx={{ marginTop: 2 }}>
      {/* Title and Add Button in the same line */}
      <Box sx={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: 1 }}>
        <Typography variant="h6" sx={{ fontSize: "16px" }}>
          Add Questions
        </Typography>
        <IconButton color="primary" onClick={addNewQuestion}>
          <AddCircleOutlineIcon />
        </IconButton>
      </Box>

      <Paper sx={{ padding: 2, marginBottom: 3 }}>
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
                value={question.defaultAnswer.toString()}
                onChange={(e) => handleAnswerChange(index, e.target.value)}
              >
                <FormControlLabel value="true" control={<Radio />} label="Yes" />
                <FormControlLabel value="false" control={<Radio />} label="No" />
              </RadioGroup>
            </Box>
            <IconButton color="secondary" onClick={() => removeQuestion(index)} sx={{ ml: 1 }}>
              <RemoveCircleOutlineIcon />
            </IconButton>
          </Box>
        ))}
      </Paper>

      {/* Submit button remains at the bottom */}
      <Button variant="contained" color="primary" onClick={handleAddQuestions}>
        Add Questions
      </Button>
    </Box>
  );
};

export default QuestionForm;
