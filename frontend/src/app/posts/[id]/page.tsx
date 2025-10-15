import PostCard from "@/components/post/PostCard";
import CommentList from "@/components/comment/CommentList";
import CommentForm from "@/components/comment/CommentForm";
import { PostService } from "@/services/postService";

interface Props {
  params: { id: string };
}

export default async function PostPage({ params }: Props) {
  const post = await PostService.getById(Number(params.id));

  return (
    <div>
      <PostCard post={post} />
      <CommentForm postId={post.id} />
      <CommentList postId={post.id} />
    </div>
  );
}
