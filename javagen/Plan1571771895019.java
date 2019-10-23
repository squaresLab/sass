public class Plan1571771895019 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("A");
}

StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
