import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import type { TaskRequest } from '../types/Task.ts';
import { createTask } from '../api/TaskApi.tsx';

/**
 * Renders a form for creating a new task.
 * - Uses local state to manage form inputs (title, description, status, dueDate)
 * - Calls the frontend API (createTask) on form submission
 * - Navigates to a success page upon successful task creation
 *
 */

export default function TaskForm() {
  const navigate = useNavigate();

  const [form, setForm] = useState<TaskRequest>({
    title: '',
    description: '',
    status: 'OPEN',
    dueDate: '',
  });

  // update form state when user types or selects an option
  const handleChange = (
    e: React.ChangeEvent<
      HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement
    >
  ) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // handle form submission and call backend api
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const createdTask = await createTask(form);
      navigate('/success', { state: { task: createdTask } });
    } catch (error) {
      alert('Error creating task');
    }
  };

  return (
    <div className="flex min-h-screen items-center justify-center">
      <form
        onSubmit={handleSubmit}
        className="w-[70vw] max-w-lg rounded-xl bg-white p-8 shadow-lg"
      >
        <h1 className="mb-6 text-center text-3xl font-bold text-gray-800">
          Create a New Task
        </h1>

        <div className="mb-4">
          <label
            htmlFor="title"
            className="mb-2 block font-semibold text-gray-700"
          >
            Title
          </label>
          <input
            id="title"
            name="title"
            required
            value={form.title}
            onChange={handleChange}
            className="w-full rounded border border-gray-300 p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="description"
            className="mb-2 block font-semibold text-gray-700"
          >
            Description
          </label>
          <textarea
            id="description"
            name="description"
            value={form.description}
            onChange={handleChange}
            className="h-32 w-full rounded border border-gray-300 p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <div className="mb-4 flex gap-4">
          <div className="flex-1">
            <label className="mb-2 block font-semibold text-gray-700">
              Status
            </label>
            <select
              name="status"
              value={form.status}
              onChange={handleChange}
              className="w-full rounded border border-gray-300 p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
            >
              <option value="OPEN">OPEN</option>
              <option value="IN_PROGRESS">IN_PROGRESS</option>
              <option value="DONE">DONE</option>
            </select>
          </div>

          <div className="flex-1">
            <label className="mb-2 block font-semibold text-gray-700">
              Due Date
            </label>
            <input
              type="datetime-local"
              name="dueDate"
              required
              value={form.dueDate}
              onChange={handleChange}
              className="w-full rounded border border-gray-300 p-3 focus:outline-none focus:ring-2 focus:ring-blue-400"
            />
          </div>
        </div>

        <button
          type="submit"
          className="mt-6 w-full rounded-lg bg-slate-600 py-3 font-bold text-white transition-colors duration-200 hover:bg-blue-700"
        >
          Submit Task
        </button>
      </form>
    </div>
  );
}
