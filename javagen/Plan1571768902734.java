public class Plan1571768902734 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseTraffic("C");
}


}

}
}
