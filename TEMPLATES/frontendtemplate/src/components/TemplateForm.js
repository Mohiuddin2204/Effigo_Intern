import React, { useState, useEffect } from "react";
import { TextField, Button, MenuItem, Box, Typography, Paper } from "@mui/material";
import { createTemplate, fetchTemplates } from "../services/api";
import { useNavigate } from "react-router-dom";

const TemplateForm = ({ onTemplateSelect }) => {
  const [templateName, setTemplateName] = useState("");
  const [templates, setTemplates] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchTemplates().then((res) => setTemplates(res.data));
  }, []);

  const handleCreateTemplate = async () => {
    if (!templateName.trim()) {
      alert("Template name cannot be empty");
      return;
    }

    try {
      const response = await createTemplate({ templateName, status: "Active", createdBy: "Admin" });
      const newTemplateId = response.data.id;

      navigate("/template-question", {
        state: {
          templateId: newTemplateId,
          templateName: templateName,
        },
      });

      setTemplateName("");
    } catch (error) {
      console.error("Error creating template:", error);
      alert("Error creating template. Please try again.");
    }
  };

  return (
    <Box sx={{ maxWidth: "500px", margin: "auto", marginTop: 4 }}>
      

      {/* CREATE TEMPLATE SECTION */}
      <Paper elevation={3} sx={{ padding: 3, borderRadius: 2, backgroundColor: "#f9f9f9", marginBottom: 3 }}>
        <Typography variant="body1" sx={{ fontWeight: "bold", marginBottom: 2 }}>
          Create a New Template
        </Typography>
        <TextField
          label="New Template Name"
          fullWidth
          value={templateName}
          onChange={(e) => setTemplateName(e.target.value)}
          sx={{ marginBottom: 2 }}
        />
        <Button
          variant="contained"
          sx={{
            textTransform: "none",
            backgroundColor: "#1976D2",
            "&:hover": { backgroundColor: "#115293" },
            fontWeight: "bold",
            padding: "10px 20px",
            fontSize: "16px",
            width: "100%",
          }}
          onClick={handleCreateTemplate}
        >
          Create Template
        </Button>
      </Paper>

      {/* ADD EXISTING TEMPLATE SECTION */}
      <Paper elevation={3} sx={{ padding: 3, borderRadius: 2, backgroundColor: "#f9f9f9" }}>
        <Typography variant="body1" sx={{ fontWeight: "bold", marginBottom: 2 }}>
          Add Existing Template
        </Typography>
        <TextField
          select
          label="Select Template"
          fullWidth
          onChange={(e) => onTemplateSelect(e.target.value)}
        >
          {templates.map((t) => (
            <MenuItem key={t.id} value={t.id}>
              {t.templateName}
            </MenuItem>
          ))}
        </TextField>
      </Paper>

    </Box>
  );
};

export default TemplateForm;
