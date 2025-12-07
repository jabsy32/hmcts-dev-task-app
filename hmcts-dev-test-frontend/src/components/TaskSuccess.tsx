import { useLocation, useNavigate } from 'react-router-dom';
import type { TaskResponse } from '../types/Task.ts';

/**
 * Displays the details of a successfully created task.
 * - Retrieves the task data from React Router location state
 * - Formats the due date for display
 * - Provides a button to navigate back to the task creation form
 */

export default function TaskSuccess() {
  const navigate = useNavigate();
  const location = useLocation();
  const task = location.state?.task as TaskResponse;

  // Redirects home if task data is missing
  if (!task)
    return (
      <div className="flex min-h-screen items-center justify-center bg-gray-50">
        <p className="text-lg text-gray-700">No task data available.</p>
      </div>
    );

  // Format due date for display in "dd/MM/yyyy HH:mm" 24-hour format
  const formattedDateTime = new Date(task.dueDate).toLocaleString('en-GB', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    hour12: false,
  });

  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-50">
      <div className="w-[70vw] max-w-lg rounded-xl bg-white p-8 text-gray-800 shadow-lg">
        <h1 className="mb-6 text-center text-3xl font-bold text-black">
          Task Created Successfully!
        </h1>

        <div className="mb-4">
          <p>
            <strong>Title:</strong> {task.title}
          </p>
        </div>
        <div className="mb-4">
          <p>
            <strong>Description:</strong> {task.description || 'None'}
          </p>
        </div>
        <div className="mb-4">
          <p>
            <strong>Status:</strong> {task.status}
          </p>
        </div>
        <div className="mb-6">
          <p>
            <strong>Due Date:</strong> {formattedDateTime}
          </p>
        </div>

        <button
          onClick={() => navigate('/')}
          className="w-full rounded-lg bg-slate-600 py-3 font-bold text-white transition-colors duration-200 hover:bg-blue-700"
        >
          Create Another Task
        </button>
      </div>
    </div>
  );
}
