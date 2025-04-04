import axios from "axios";

const API_BASE_URL = "http://localhost:8082/api";

export const createTemplate = (data) => axios.post(`${API_BASE_URL}/templates`, data);
export const fetchTemplates = () => axios.get(`${API_BASE_URL}/templates`);
export const addQuestion = (data) => axios.post(`${API_BASE_URL}/questions`, data);
export const setDefaultAnswer = (data) => axios.post(`${API_BASE_URL}/answers`, data);
export const fetchTemplateQuestions = (templateId) => axios.get(`${API_BASE_URL}/templates/${templateId}/questions`);

// Existing Bulk API
export const addQuestionsBulk = (data) => axios.post(`${API_BASE_URL}/questions/bulk-with-answers`, data);

// ✅ New API for adding bulk questions for a template
export const addTemplateQuestionsBulk = (templateId, data) =>
    axios.post(`${API_BASE_URL}/templates/${templateId}/quesans/bulk`, data);
