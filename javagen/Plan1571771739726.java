public class Plan1571771739726 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


} else {
IncreaseTraffic("B");
}

}

}
}
