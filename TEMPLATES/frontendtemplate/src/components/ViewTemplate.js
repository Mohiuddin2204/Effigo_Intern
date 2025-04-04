import React, { useState, useEffect } from "react";
import { FormControl, InputLabel, Select, MenuItem, Grid } from "@mui/material";
import { fetchTemplates } from "../services/api";
import QuestionSection from "./QuestionSection";  // ✅ Import QuestionSection

const ViewTemplate = () => {
  const [templates, setTemplates] = useState([]);
  const [selectedTemplate, setSelectedTemplate] = useState("");

  useEffect(() => {
    fetchTemplates().then((res) => setTemplates(res.data));
  }, []);

  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <FormControl fullWidth>
          <InputLabel>Select a Template</InputLabel>
          <Select value={selectedTemplate} onChange={(e) => setSelectedTemplate(e.target.value)}>
            {templates.map((template) => (
              <MenuItem key={template.id} value={template.id}>{template.templateName}</MenuItem>
            ))}
          </Select>
        </FormControl>
      </Grid>

      {/* ✅ Show QuestionSection only after template selection */}
      {selectedTemplate && (
        <Grid item xs={12}>
          <QuestionSection templateId={selectedTemplate} />
        </Grid>
      )}
    </Grid>
  );
};

export default ViewTemplate;