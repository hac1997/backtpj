'use client';
import { useEffect, useState } from "react";
import { CommentService } from "@/services/commentService";
import { Comment } from "@/types/comment";

interface CommentListProps {
  postId: number;
}

export default function CommentList({ postId }: CommentListProps) {
  const [comments, setComments] = useState<Comment[]>([]);

  useEffect(() => {
    CommentService.getByPost(postId).then(setComments);
  }, [postId]);

  return (
    <div className="mt-4">
      <h3 className="font-semibold mb-2">Comentários</h3>
      {comments.length === 0 && (
        <p className="text-gray-500">Nenhum comentário ainda.</p>
      )}
      <ul className="space-y-3">
        {comments.map((c) => (
          <li key={c.id} className="bg-gray-100 p-3 rounded-lg">
            <p className="text-gray-800">{c.body}</p>
            <div className="text-sm text-gray-500 mt-1">
              {c.author} — {new Date(c.createdAt).toLocaleString()}
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}
