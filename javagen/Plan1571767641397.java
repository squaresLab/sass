public class Plan1571767641397 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}


for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}



}
}
