public class Plan1571775714183 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

StartServer("A");


for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


}
}
