import React, { useState, useEffect } from "react";
import {
  List, ListItem, ListItemText, Button, Typography, Box, Paper
} from "@mui/material";
import { fetchTemplateQuestions } from "../services/api";

const QuestionList = ({ templateId, refresh }) => {
  const [questions, setQuestions] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const questionsPerPage = 5;

  useEffect(() => {
    if (templateId) {
      fetchTemplateQuestions(templateId).then((res) => setQuestions(res.data));
    }
  }, [templateId, refresh]);

  const paginatedQuestions = questions.slice(
    currentPage * questionsPerPage,
    (currentPage + 1) * questionsPerPage
  );

  return (
    <Box sx={{ marginTop: 3, maxWidth: "800px", marginLeft: "20px" }}>
      <Typography variant="h6" sx={{ fontSize: "18px", fontWeight: "bold", marginBottom: 2 }}>
        Questions
      </Typography>
      <List sx={{ padding: 0 }}>
        {paginatedQuestions.map((q) => (
          <Paper
            key={q.id}
            elevation={3}
            sx={{
              padding: 2,
              marginBottom: 2,
              borderRadius: 2,
              backgroundColor: "#f9f9f9",
              borderLeft: "5px solid #1976D2",
              width: "80%", // Making sure it aligns to the left properly
            }}
          >
            <ListItem sx={{ display: "block", padding: 0 }}>
              <ListItemText
                primary={
                  <Typography sx={{ fontSize: "16px", fontWeight: "bold", color: "black" }}>
                    {q.questionName}
                  </Typography>
                }
                secondary={
                  <Typography sx={{ fontSize: "14px", color: "#1976D2", marginTop: "5px" }}>
                    Answer: {q.defaultAnswer?.defaultAnswer ? "Yes" : "No"}
                  </Typography>
                }
              />
            </ListItem>
          </Paper>
        ))}
      </List>
      
      {/* Pagination Buttons */}
      <Box sx={{ display: "flex", justifyContent: "space-between", marginTop: 2, width: "80%" }}>
        <Button
          variant="contained"
          disabled={currentPage === 0}
          onClick={() => setCurrentPage((prev) => prev - 1)}
        >
          Previous
        </Button>
        <Button
          variant="contained"
          disabled={(currentPage + 1) * questionsPerPage >= questions.length}
          onClick={() => setCurrentPage((prev) => prev + 1)}
        >
          Next
        </Button>
      </Box>
    </Box>
  );
};

export default QuestionList;
