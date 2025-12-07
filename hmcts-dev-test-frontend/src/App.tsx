import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SuccessPage from './pages/SuccessPage';
import CreateTaskPage from './pages/createTaskPage.tsx';

/**
 * Main entry point of the React application.
 * Sets up client-side routing using react-router-dom:
 * - "/" renders the task creation page
 * - "/success" renders the task success page
 */

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<CreateTaskPage />} />
        <Route path="/success" element={<SuccessPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
