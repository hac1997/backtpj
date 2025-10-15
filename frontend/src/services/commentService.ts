import { api } from "./api";
import { Comment } from "@/types/comment";

export const CommentService = {
  getByPost: async (postId: number): Promise<Comment[]> => {
    const response = await api.get(`/posts/${postId}/comments`);
    return response.data;
  },
  create: async (postId: number, data: Partial<Comment>) => {
    const response = await api.post(`/posts/${postId}/comments`, data);
    return response.data;
  },
};
