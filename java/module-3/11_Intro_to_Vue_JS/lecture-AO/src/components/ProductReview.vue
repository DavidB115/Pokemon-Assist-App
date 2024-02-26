<template>
    <div class="main">
        <h2>Product Reviews for {{ name }}</h2>

        <p class="description"> {{ description }} </p>
        
        <div class="review"
            v-for="review in reviews"
            v-bind:key="review.id">
            <h4>{{ review.reviewer }}</h4>
            <div class="rating">
             <img
                src="../assets/star.png"
                v-bind:title="review.rating + ' Star Review'"
                class="ratingStar"
                v-for="n in review.rating"
                v-bind:key="n"
            />
            </div>
            <h3> {{ review.title }} </h3>
            <p> {{ review.review }} </p>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: 'Head First Design Patterns',
            description: 'A Brain Friendly Guide to Design Patterns',
            reviews: [
                        {
                        id: 1000,
                        reviewer: 'R Pérez',
                        title: 'Approachable pattern guide',
                        review:
                            'I love the uncomplicated, informal narrative style. I highly recommend this text for anyone trying to understand Design Patterns in a super simple way.',
                        rating: 4,
                        favorited: false
                        },
                        {
                        id: 1001,
                        reviewer: 'Carmen',
                        title: 'Pattern complexity gone!',
                        review:
                            'I struggled for years to understand patterns and how to implement the design and how them. This is by far the best book to learn design patterns. I would give this 10 stars if I could.',
                        rating: 5,
                        favorited: false
                        },
                        {
                        id: 1002,
                        reviewer: 'J. King',
                        title: 'Not for me',
                        review:
                            'The content is thorough and well described. However, the format just doesn\'t work for me. I need something more traditional.',
                        rating: 1,
                        favorited: false
                        },
                        {
                        id: 1003,
                        reviewer: 'Safa H.',
                        title: 'Good for beginners',
                        review:
                            'Good introduction to design patterns especially if you have never used them before or are relatively new to OO principles.',
                        rating: 3,
                        favorited: false
                        },
                        {
                        id: 1004,
                        reviewer: 'L Wang',
                        title: 'Entertaining',
                        review:
                            'If you are new to design patterns I HIGHLY recommend this book. You might think it\'s not "serious enough" at first. But as you go through it things just stick. It makes learning enjoyable.',
                        rating: 4,
                        favorited: false
                        }
                    ]
        };
    }
}
</script>

<style scoped>
    .main {
        margin: 1rem 0;
    }
    .rating {
        height: 2rem;
        display: inline-block;
        vertical-align: top;
        margin: 0 0.5rem;
    }

    .rating img {
        height: 100%;
    }

    .review {
        border: 1px black solid;
        border-radius: 6px;
        padding: 1rem;
        margin: 10px;
    }

    .review p {
        display: inline-block;
    }

    .review h4 {
        font-size: 1rem;
    }

</style>