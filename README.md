# پیمایشگر گراف
در این پروژه، یک پیمایشگر ساده گراف با دو روش اول-سطح و اول-عمق پیاده سازی شده است.

## بخش اول - پیاده‌سازی نوع Adapter

### زیربخش ۱ - انتخاب نوع Adapter
...

### زیربخش ۲ - نحوه‌ی پیاده‌سازی الگو
...

## بخش دوم - تغییر کتابخانه

### زیربخش ۱
...

### زیربخش ۲
...

## بخش سوم - تحلیل الگوی Strategy

### سوال ۱:‌استفاده از این الگو به چه علت قابل قبول است؟
توجه کنید که الگوی Strategy در اینجا مناسب است زیرا ما روش‌های متفاوتی برای traverse کردن و پیمایش یک گراف داریم. ضمنا، ممکن است در ادامه‌ی develop و maintain این پروژه، بخواهیم روش‌های جدیدتر و به‌روزتر را بنابر نیاز پیاده سازی و استفاده کنیم (در جاهای مختلفی). این یعنی، ما باید یک interface واحد داشته باشیم که به ما اجازه بدهد تا هر کلاسی که خواستیم تعریف کنیم‌(از یک الگوریتم جدید traverse) بتوانیم با این interface آنرا توصیف کنیم و درواقع از این interface استفاده (implement) کنیم. در اینجا، interface ما بصورت زیر است:

```java
public interface Traverser {
    List<Integer> traverse(Integer startVertex);
}
```

حالا، با استفاده از این interface می‌توانیم روش‌های مختلف traverse گراف را پیاده‌سازی کنیم و از هر روشی در هرجایی بنابر نیاز استفاده کنیم (استراتژی‌های مختلف برای انجام پیمایش گراف). درنتیجه، در اینجا استفاده از الگوی Strategy منطقی است.

### سوال ۲: روش تحقق این الگو را به صورت مختصر توضیح دهید.
همانطور که پیش‌تر اشاره شد، برای تحقق این الگو، یک interface برای توضیف انواع روش های پیمایش می خواهیم که `Traverser` نام دارد و یک تابع دارد که `traverse` نام دارد. این تابع، بعنوان ورودی یک راس از گراف را می‌گیرد (index یا شماره‌ی آن) و از آن راس شروع به پیمایش می کند. ضمنا خروجی آن، لیست شماره‌ی رئوسی است که در این پیمایش به ترتیب دیده شده‌اند. (به ترتیب) حالا، باید روش‌های مختلف پیمایش را که در اینجا مثلا BFS و DFS هستند با استفاده از این ineterface (و implement کردن آن) پیاده‌سازی کنیم. در نهایت، در هرجای برنامه که نیاز به یک یا چند نوع از این traverser ها داشتیم، از انها می‌توانیم استفاده کنیم. مثلا در Main داریم:

```java
Traverser dfsGraphTraveler= new DfsGraphTraverser(graph);
Traverser bfsGraphTraveler= new BfsGraphTraverser(graph);

List<Integer> dfsPath = dfsGraphTraveler.traverse(1);
List<Integer> bfsTraveler = bfsGraphTraveler.traverse(1);

System.out.println("Graph-DFS From node 1 is : " + dfsPath);
System.out.println("Graph-BFS From node 1 is : " + bfsTraveler);
```

### توضیحاتی اضافه‌تر درمورد استفاده از Strategy
در این پروژه، استفاده از الگوی Strategy برای ما بسیار کارآمد است. زیرا با استفاده از آن به موارد زیر دست می‌یابیم:
- قابلیت Interchange

  مثلا در جایی که می‌خواهیم از یک روش traverse به روش دیگری تغییر دهیم، می‌توانیم به راحتی این کار را انجام دهیم. مثلا اگر بخواهیم از DFS به BFS تغییر دهیم، کافی است که یک instance از `BfsGraphTraverser` بسازیم و در جایی که از `DfsGraphTraverser` استفاده می‌کردیم، از این instance استفاده کنیم.

- Encapsulation

هر روش پیمایش، توسط کلاسی که آنرا توصیف می‌کند encapsulate شده است. همچنین، جزئیات پیاده‌سازی هر روش پیمایش از دید کاربر مخفی است و کاربر فقط با استفاده از interface `Traverser` می‌تواند از آن استفاده کند. 

- Extensibility

با استفاده از این الگو، می‌توانیم روش‌های جدیدی برای پیمایش گراف پیاده‌سازی کنیم و در هرجای برنامه که نیاز به این روش‌ها داشتیم، از آنها استفاده کنیم. این کار، به سادگی این است که پس از تعریف روش جدید، در هرجای برنامه که نیاز به آن داشتیم، یک instance از آن بسازیم و از آن استفاده کنیم.‌ (چون محل اصلی صدا زدن آن، خمچنان تابع `traverse` است.)

- Runtime flexibility

با این الگو، می‌توانیم در زمان اجرا، روش پیمایش را تغییر دهیم. مثلا اگر در زمان اجرا بخواهیم از DFS به BFS تغییر دهیم، کافی است که یک instance از `BfsGraphTraverser` بسازیم و در جایی که از `DfsGraphTraverser` استفاده می‌کردیم، از این instance استفاده کنیم.

با این توضیحات، فرض کنید بخواهیم یک الگوریتم جدید برای پیمایش به برنامه‌مان اضافه کنیم. این کار به همین سادگی است:

```java

class NewGraphTraverser implements Traverser {
    private Graph graph;

    public NewGraphTraverser(Graph graph) {
        this.graph = graph;
    }

    @Override
    public List<Integer> traverse(Integer startVertex) {
        // A dummy algorithm for example
        List<Integer> path = new ArrayList<>();
        path.add(startVertex);

        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (i != startVertex && graph.isAdjacent(startVertex, i)) {
                path.add(i);
            }
        }
        return path;
    }
}
```

```java
Traverser newGraphTraverser = new NewGraphTraverser(graph);
List<Integer> newPath = newGraphTraverser.traverse(1);
System.out.println("Graph-New From node 1 is : " + newPath);
```
