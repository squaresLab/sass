public class Plan1571767632948 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

StartServer("A");
for (int i = 0; i < 4 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

}




}
}
