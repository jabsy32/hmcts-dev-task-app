// Data sent from frontend to backend when creating a task
export interface TaskRequest {
  title: string;
  description?: string;
  status: string;
  dueDate: string;
}

// Data returned from backend after task creation
export interface TaskResponse {
  id: number;
  title: string;
  description?: string;
  status: string;
  dueDate: string;
}
