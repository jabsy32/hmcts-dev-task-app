import type { TaskRequest, TaskResponse } from '../types/Task.ts';

/**
 * Frontend API module for interacting with the Task backend.
 * Contains functions to send HTTP requests and handle responses.
 *
 * Sends a POST request to create a new task and returns the created task
 */

export async function createTask(task: TaskRequest): Promise<TaskResponse> {
  const response = await fetch('/tasks', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(task),
  });

  if (!response.ok) {
    throw new Error('Failed to create task');
  }

  return response.json();
}
