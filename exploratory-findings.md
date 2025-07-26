# Exercise 1 | Exploratory testing summary

As part of the exploratory testing on the Qubika Sports Club Management System, the following UI/UX, functional, and API-related issues were observed. These insights are based on hands-on interaction with the system and supported by visual evidence from screenshots. Please review the items below:

## Issues Identified

1. Pagination Overlap in “Tipos de Categorías”
    The page number controls at the bottom overlap with the sidebar, particularly around high page numbers (e.g., pages 334–371).
    **Improvement Suggestion:** Adjust pagination styles to avoid overlay with the sidebar and ensure consistent behavior across modules.

2. API Allows Duplicate Category Creation
    The endpoint POST /api/category-type/create does not validate whether a category already exists.
    **Improvement Suggestion:** Introduce validations to avoid unintentional duplicate entries.

3. Incorrect Field Naming in Add Category Modal
    The input field for category name uses an ID named input-username, which is misleading.
    **Improvement Suggestion:** Use  naming like input-category-name to improve code readability and maintainability.

4. Multiple Simultaneous User Sessions
    A user can log in simultaneously from multiple browsers (e.g., Chrome and Edge) without invalidating previous sessions.
    **Observation:** This could pose a security concern depending on the application’s session management requirements.

5. Filter by “Documento” Not Working in “Miembros” Module
    Applying filters using the Documento field returns no results even when valid document numbers exist in the table.
    **Improvement Suggestion:** Review filtering logic and ensure backend API supports this field for filtering.
