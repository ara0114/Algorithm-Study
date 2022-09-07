def solution(sizes):
    w_sizes = []
    h_sizes = []

    for size in sizes:
        if size[0] > size[1]:
            w_sizes.append(size[0])
            h_sizes.append(size[1])
        else:
            w_sizes.append(size[1])
            h_sizes.append(size[0])

    answer = max(w_sizes) * max(h_sizes)

    return answer
