public class Plan1571772954225 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

DecreaseTraffic("A");

}



}
}
