public class Plan1571772335726 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("A");
for (int i = 0; i < 6 ; i++) {
StartServer("B");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



for (int i = 0; i < 2 ; i++) {
StartServer("A");
DecreaseTraffic("A");

}



}
}
