
import React, { useState } from "react";
import { Box } from "@mui/material";
import QuestionForm from "./QuestionForm";
import QuestionList from "./QuestionList";

const QuestionSection = ({ templateId }) => {
  const [refresh, setRefresh] = useState(false);

  return (
    <Box sx={{ marginTop: 3 }}>
      <QuestionForm templateId={templateId} onQuestionAdded={() => setRefresh(!refresh)} />
      <QuestionList templateId={templateId} refresh={refresh} />
    </Box>
  );
};

export default QuestionSection;