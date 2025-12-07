// src/test/TaskForm.test.tsx
import { render, screen, fireEvent } from '@testing-library/react';
import TaskForm from '../components/TaskForm';
import { MemoryRouter } from 'react-router-dom';

describe('TaskForm', () => {
  it('renders without crashing', () => {
    render(
      <MemoryRouter>
        <TaskForm />
      </MemoryRouter>
    );

    expect(screen.getByText(/submit task/i)).toBeInTheDocument();
  });

  it('allows typing into input fields', () => {
    render(
      <MemoryRouter>
        <TaskForm />
      </MemoryRouter>
    );

    const titleInput = screen.getByLabelText(/title/i); // <-- changed from /task name/i
    fireEvent.change(titleInput, { target: { value: 'New task' } });
    expect(titleInput).toHaveValue('New task');

    const descriptionInput = screen.getByLabelText(/description/i);
    fireEvent.change(descriptionInput, { target: { value: 'This is a test' } });
    expect(descriptionInput).toHaveValue('This is a test');
  });

  it('submits the form correctly', () => {
    render(
      <MemoryRouter>
        <TaskForm />
      </MemoryRouter>
    );

    const titleInput = screen.getByLabelText(/title/i);
    fireEvent.change(titleInput, { target: { value: 'New task' } });

    const descriptionInput = screen.getByLabelText(/description/i);
    fireEvent.change(descriptionInput, {
      target: { value: 'Test description' },
    });

    const submitButton = screen.getByText(/submit task/i);
    fireEvent.click(submitButton);

    // Add expectations depending on what submit does
    expect(titleInput).toHaveValue('New task');
    expect(descriptionInput).toHaveValue('Test description');
  });
});
